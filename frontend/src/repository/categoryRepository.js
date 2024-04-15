import axios from '../custom-axios/axios';

const categoryService = {

    fetchCategories: () => {
        return axios.get("/categories")
    }
}

export default categoryService;