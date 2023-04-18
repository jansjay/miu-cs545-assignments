import axios from "axios";

const mainEndPoint = "http://localhost:8000";
export const apiGet = async (url, data) => {
  let axiosData = await axios.get(mainEndPoint + url, data);
  return axiosData.data;
};

export const apiDelete = async (url) => {
  let axiosData = await axios.delete(mainEndPoint + url);
  return axiosData.data;
};

export const apiPost = async (url, data) => {
  let axiosData = await axios.post(mainEndPoint + url, data);
  return axiosData.data;
};

export const apiPut = async (url, data) => {
  let axiosData = await axios.put(mainEndPoint + url, data);
  return axiosData.data;
};
