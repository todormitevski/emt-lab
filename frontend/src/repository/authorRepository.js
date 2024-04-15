import axios from "../custom-axios/axios";

const authorService = {

    fetchAuthors: () => {
        return axios.get("/authors")
    }
}

export default authorService;