import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'

export default function RecruiterDashboardPage() {
  const [jobs, setJobs] = useState([])
  const [jobForm, setJobForm] = useState({ title: '', description: '', type: 'JOB', location: '', minimumCgpa: '', requiredSkills: '' })
  const [applicants, setApplicants] = useState([])

  const load = async () => {
    const res = await client.get('/recruiters/jobs')
    setJobs(res.data.data)
  }

  useEffect(() => {
    load().catch(() => toast.error('Failed to load recruiter dashboard'))
  }, [])

  const createJob = async (e) => {
    e.preventDefault()
    await client.post('/recruiters/jobs', {
      ...jobForm,
      minimumCgpa: jobForm.minimumCgpa ? Number(jobForm.minimumCgpa) : null,
      requiredSkills: jobForm.requiredSkills ? jobForm.requiredSkills.split(',').map((s) => s.trim()) : []
    })
    toast.success('Job posted')
    setJobForm({ title: '', description: '', type: 'JOB', location: '', minimumCgpa: '', requiredSkills: '' })
    await load()
  }

  const viewApplicants = async (jobId) => {
    const res = await client.get(`/recruiters/jobs/${jobId}/applicants`)
    setApplicants(res.data.data)
  }

  const updateStatus = async (applicationId, status) => {
    await client.patch(`/recruiters/applications/${applicationId}`, null, { params: { status } })
    toast.success('Updated')
    setApplicants((prev) => prev.map((a) => (a.id === applicationId ? { ...a, status } : a)))
  }

  return (
    <main className="container">
      <h2>Recruiter Dashboard</h2>
      <form className="card" onSubmit={createJob}>
        <h3>Create Job</h3>
        <input placeholder="Title" value={jobForm.title} onChange={(e) => setJobForm({ ...jobForm, title: e.target.value })} required />
        <textarea placeholder="Description" value={jobForm.description} onChange={(e) => setJobForm({ ...jobForm, description: e.target.value })} required />
        <div className="row">
          <select value={jobForm.type} onChange={(e) => setJobForm({ ...jobForm, type: e.target.value })}><option value="JOB">JOB</option><option value="INTERNSHIP">INTERNSHIP</option></select>
          <input placeholder="Location" value={jobForm.location} onChange={(e) => setJobForm({ ...jobForm, location: e.target.value })} />
          <input placeholder="Minimum CGPA" type="number" step="0.1" value={jobForm.minimumCgpa} onChange={(e) => setJobForm({ ...jobForm, minimumCgpa: e.target.value })} />
        </div>
        <input placeholder="Required skills (comma separated)" value={jobForm.requiredSkills} onChange={(e) => setJobForm({ ...jobForm, requiredSkills: e.target.value })} />
        <button type="submit">Post Job</button>
      </form>

      <h3>My Jobs</h3>
      <div className="grid cols-2">
        {jobs.map((j) => <article className="card" key={j.id}><h4>{j.title}</h4><p>{j.location}</p><button onClick={() => viewApplicants(j.id)}>View Applicants</button></article>)}
      </div>

      <h3>Applicants</h3>
      <table className="table">
        <thead><tr><th>Student</th><th>Job</th><th>Status</th><th>Resume</th><th>Action</th></tr></thead>
        <tbody>
          {applicants.map((a) => (
            <tr key={a.id}>
              <td>{a.studentName}</td>
              <td>{a.jobTitle}</td>
              <td>{a.status}</td>
              <td>{a.resumePath ? <a href={`http://localhost:8080${a.resumePath}`} target="_blank" rel="noreferrer">Download</a> : 'N/A'}</td>
              <td>
                <button onClick={() => updateStatus(a.id, 'SHORTLISTED')}>Shortlist</button>
                <button onClick={() => updateStatus(a.id, 'REJECTED')}>Reject</button>
                <button onClick={() => updateStatus(a.id, 'SELECTED')}>Select</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  )
}
