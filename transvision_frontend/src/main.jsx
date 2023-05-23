import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import SignIn from './components/SignIn.jsx';
import ErrorPage from './components/ErrorPage.jsx';
import AdminUserPage from './components/AdminUserPage.jsx';
import GeneralUserPage from './components/GeneralUserPage.jsx';
import SignUp from './components/SignUp.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <SignIn/>,
    errorElement: <ErrorPage/>
  },
  {
    path: "/signup",
    element: <SignUp/>,
    errorElement: <ErrorPage/>
  },
  {
    path: "admin/:adminId",
    element: <AdminUserPage />,
    errorElement: <ErrorPage/>
  },
  {
    path: "general/:userId",
    element: <GeneralUserPage />,
    errorElement: <ErrorPage/>
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
