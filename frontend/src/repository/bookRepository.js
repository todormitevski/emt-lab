import axios from '../custom-axios/axios';

const bookService = {
    fetchBooks: () => {
        return axios.get("/books")
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    takeBook: (id) => {
        return axios.put(`/books/take/${id}`)
    },
    searchBook: () => {
        return axios.get(`/books/search`)
    }
}

export default bookService;