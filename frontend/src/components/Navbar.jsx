import { Link, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

export default function Navbar() {
  const { user, logout, toggleTheme, theme } = useAuth()
  const navigate = useNavigate()

  const logoutNow = () => {
    logout()
    navigate('/login')
  }

  return (
    <nav className="navbar">
      <Link to="/" className="brand">Placement Portal</Link>
      <div className="nav-links">
        {user && <Link to="/jobs">Jobs</Link>}
        {user?.role === 'STUDENT' && <Link to="/student">Student Dashboard</Link>}
        {user?.role === 'RECRUITER' && <Link to="/recruiter">Recruiter Dashboard</Link>}
        {user?.role === 'ADMIN' && <Link to="/admin">Admin Dashboard</Link>}
        {user && (user.role === 'ADMIN' || user.role === 'RECRUITER') && <Link to="/analytics">Analytics</Link>}
        {user && user.role !== 'ADMIN' && <Link to="/profile">Profile</Link>}
        <button onClick={toggleTheme} className="btn-secondary">{theme === 'light' ? 'Dark' : 'Light'} Mode</button>
        {!user ? <Link to="/login">Login</Link> : <button onClick={logoutNow}>Logout</button>}
      </div>
    </nav>
  )
}
