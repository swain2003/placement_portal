import { Navigate, Route, Routes } from 'react-router-dom'
import HomePage from './pages/HomePage'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import JobsPage from './pages/JobsPage'
import StudentDashboardPage from './pages/StudentDashboardPage'
import RecruiterDashboardPage from './pages/RecruiterDashboardPage'
import AdminDashboardPage from './pages/AdminDashboardPage'
import ProfilePage from './pages/ProfilePage'
import AnalyticsPage from './pages/AnalyticsPage'
import Navbar from './components/Navbar'
import ProtectedRoute from './components/ProtectedRoute'

export default function App() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/jobs" element={<ProtectedRoute roles={['STUDENT', 'RECRUITER', 'ADMIN']}><JobsPage /></ProtectedRoute>} />
        <Route path="/profile" element={<ProtectedRoute roles={['STUDENT', 'RECRUITER']}><ProfilePage /></ProtectedRoute>} />
        <Route path="/analytics" element={<ProtectedRoute roles={['ADMIN', 'RECRUITER']}><AnalyticsPage /></ProtectedRoute>} />
        <Route path="/student" element={<ProtectedRoute roles={['STUDENT']}><StudentDashboardPage /></ProtectedRoute>} />
        <Route path="/recruiter" element={<ProtectedRoute roles={['RECRUITER']}><RecruiterDashboardPage /></ProtectedRoute>} />
        <Route path="/admin" element={<ProtectedRoute roles={['ADMIN']}><AdminDashboardPage /></ProtectedRoute>} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </div>
  )
}
