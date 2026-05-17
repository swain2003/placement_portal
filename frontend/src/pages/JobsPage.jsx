import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'
import { useAuth } from '../context/AuthContext'

export default function JobsPage() {
  const [jobs, setJobs] = useState([])
  const [search, setSearch] = useState('')
  const { user } = useAuth()

  const loadJobs = async () => {
    const res = await client.get('/jobs', { params: { search } })
    setJobs(res.data.data.content)
  }

  useEffect(() => {
    loadJobs().catch(() => toast.error('Unable to load jobs'))
  }, [])

  const apply = async (jobId) => {
    try {
      await client.post(`/students/jobs/${jobId}/apply`)
      toast.success('Applied successfully')
    } catch (err) {
      toast.error(err.response?.data?.message || 'Could not apply')
    }
  }

  return (
    <main className="container">
      <h2>Job Listings</h2>
      <div className="row">
        <input value={search} placeholder="Search jobs by title" onChange={(e) => setSearch(e.target.value)} />
        <button onClick={loadJobs}>Search</button>
      </div>
      <div className="grid cols-2">
        {jobs.map((job) => (
          <article className="card" key={job.id}>
            <h3>{job.title}</h3>
            <p>{job.companyName} • {job.location}</p>
            <p>{job.description}</p>
            <p><b>Skills:</b> {job.requiredSkills?.join(', ') || 'N/A'}</p>
            {user?.role === 'STUDENT' && <button onClick={() => apply(job.id)}>Apply</button>}
          </article>
        ))}
      </div>
    </main>
  )
}
