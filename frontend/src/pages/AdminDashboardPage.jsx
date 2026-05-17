import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'

export default function AdminDashboardPage() {
  const [dash, setDash] = useState(null)
  const [students, setStudents] = useState([])
  const [recruiters, setRecruiters] = useState([])

  useEffect(() => {
    Promise.all([client.get('/dashboard/admin'), client.get('/admin/students'), client.get('/admin/recruiters')])
      .then(([d, s, r]) => {
        setDash(d.data.data)
        setStudents(s.data.data)
        setRecruiters(r.data.data)
      })
      .catch(() => toast.error('Failed to load admin dashboard'))
  }, [])

  return (
    <main className="container">
      <h2>Admin Dashboard</h2>
      {dash && <section className="grid cols-4">
        <div className="card"><h3>Students</h3><p>{dash.totalStudents}</p></div>
        <div className="card"><h3>Recruiters</h3><p>{dash.totalRecruiters}</p></div>
        <div className="card"><h3>Jobs</h3><p>{dash.totalJobs}</p></div>
        <div className="card"><h3>Applications</h3><p>{dash.totalApplications}</p></div>
      </section>}
      <div className="grid cols-2">
        <article className="card">
          <h3>Students</h3>
          <ul>{students.map((s) => <li key={s.id}>{s.fullName} • {s.branch || 'N/A'}</li>)}</ul>
        </article>
        <article className="card">
          <h3>Recruiters</h3>
          <ul>{recruiters.map((r) => <li key={r.id}>{r.companyName} • {r.email}</li>)}</ul>
        </article>
      </div>
    </main>
  )
}
