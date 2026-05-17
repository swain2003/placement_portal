import { useEffect, useState } from 'react'
import { Bar, BarChart, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts'
import client from '../api/client'

export default function AnalyticsPage() {
  const [data, setData] = useState([])

  useEffect(() => {
    client.get('/dashboard/admin').then((res) => {
      const skills = res.data.data.topSkills || {}
      setData(Object.entries(skills).map(([name, count]) => ({ name, count })))
    })
  }, [])

  return (
    <main className="container">
      <h2>Placement Analytics</h2>
      <div className="card" style={{ height: 360 }}>
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data}>
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="count" fill="#4f46e5" />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </main>
  )
}
