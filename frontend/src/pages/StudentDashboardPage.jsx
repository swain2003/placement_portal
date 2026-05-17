import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import client from '../api/client'

export default function StudentDashboardPage() {
  const [dashboard, setDashboard] = useState(null)
  const [applications, setApplications] = useState([])

  useEffect(() => {
    Promise.all([client.get('/dashboard/student'), client.get('/students/applications')])
      .then(([dash, apps]) => {
        setDashboard(dash.data.data)
        setApplications(apps.data.data)
      })
      .catch(() => toast.error('Unable to load student dashboard'))
  }, [])

  return (
    <main className="container">
      <h2>Student Dashboard</h2>
      {dashboard && (
        <section className="grid cols-3">
          <div className="card"><h3>Applications</h3><p>{dashboard.totalApplications}</p></div>
          <div className="card"><h3>Selected</h3><p>{dashboard.selectedApplications}</p></div>
          <div className="card"><h3>Rejected</h3><p>{dashboard.rejectedApplications}</p></div>
        </section>
      )}
      <h3>Application Status</h3>
      <table className="table">
        <thead><tr><th>Job</th><th>Company</th><th>Status</th></tr></thead>
        <tbody>
          {applications.map((a) => <tr key={a.id}><td>{a.jobTitle}</td><td>{a.companyName}</td><td>{a.status}</td></tr>)}
        </tbody>
      </table>
    </main>
  )
}
