import { apiDelete, apiGet, apiPost, apiPut } from "../genericApiCalls";

export const getPosts = async () => {
    return apiGet("/posts");
};

export const getComments = async (id) => {
    return apiGet("/posts/"+id+"/comments");
};

export const getPost = async (id) => {
    return apiGet("/posts/" + id);
};

export const deletePost = async (id) => {
    return apiDelete("/posts/" + id);
};

export const updatePost = async (post) => {
    return apiPut("/posts", post);
};

export const addNewPost = async (post) => {
    return apiPost("/posts", post);
};
