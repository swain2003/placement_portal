import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { useAuth } from '../context/AuthContext'

export default function LoginPage() {
  const { login } = useAuth()
  const navigate = useNavigate()
  const [form, setForm] = useState({ email: '', password: '' })

  const submit = async (e) => {
    e.preventDefault()
    try {
      const data = await login(form.email, form.password)
      navigate(data.role === 'RECRUITER' ? '/recruiter' : data.role === 'ADMIN' ? '/admin' : '/student')
    } catch (err) {
      toast.error(err.response?.data?.message || 'Login failed')
    }
  }

  return (
    <main className="container small">
      <h2>Login</h2>
      <form className="card" onSubmit={submit}>
        <input placeholder="Email" type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
        <input placeholder="Password" type="password" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
        <button type="submit">Login</button>
      </form>
      <p>Demo users: admin@portal.com / Admin@123, recruiter@company.com / Recruiter@123, student@college.com / Student@123</p>
    </main>
  )
}
