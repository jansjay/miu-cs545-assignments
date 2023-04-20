import { Route, Routes } from "react-router-dom"
import Posts from "../Posts"
import { ModifyPostDetails } from "../ModifyPostDetails"
import PostDetails from "../PostDetails"
import { Welcome } from "../Welcome"

export const PageRoutes = (props) => {
    return (
        <Routes>
            <Route path="/" element={<Welcome />}/>
            <Route path="/posts" element={<Posts />}/>
            <Route path="/posts/:id" element={<PostDetails />}/>
            <Route path="/modify-post/:id" element={<ModifyPostDetails />}/>
            <Route path="/add-post" element={<ModifyPostDetails />}/> 
        </Routes>
    )
}