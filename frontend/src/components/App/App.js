import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Books from '../Books/BookList/books';
import Categories from "../Categories/categories";
import Authors from "../Authors/AuthorList/authors"
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/BookAdd"
import BookEdit from "../Books/BookEdit/BookEdit"
import bookService from "../../repository/bookRepository";
import authorService from "../../repository/authorRepository";
import categoryService from "../../repository/categoryRepository";
import BookSearch from "../Books/BookSearch/BookSearch";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div>
                        <Routes>
                            <Route path={"/books"}
                                   element={<Books books={this.state.books}
                                                   onDelete={this.deleteBook}
                                                   onEdit={this.getBook}
                                                   onTake={this.takeBook}/>}/>

                            <Route path={"/"}
                                   element={<Books books={this.state.books}
                                                   onDelete={this.deleteBook}
                                                   onEdit={this.getBook}
                                                   onTake={this.takeBook}/>}/>

                            <Route path={"/categories"}
                                   element={<Categories categories={this.state.categories}/>}/>

                            <Route path={"/authors"}
                                   element={<Authors authors={this.state.authors}/>}/>

                            <Route path={"/books/add"}
                                   element={<BookAdd categories={this.state.categories}
                                                     authors={this.state.authors}
                                                     onAddBook={this.addBook}/>}/>

                            <Route path={"/books/edit/:id"}
                                   element={<BookEdit categories={this.state.categories}
                                                      authors={this.state.authors}
                                                      onEditBook={this.editBook}
                                                      book={this.state.selectedBook}/>}/>

                            <Route path={"/books/search"}
                                   element={<BookSearch />}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    loadBooks = () => {
        bookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        categoryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadAuthors = () => {
        authorService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    deleteBook = (id) => {
        bookService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        bookService.addBook(name, category, author, availableCopies)
            .then(() => this.loadBooks());
    }

    getBook = (id) => {
        bookService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    takeBook = (id) => {
        bookService.takeBook(id)
            .then(() => this.loadBooks())
    }

    editBook = (id, name, category, author, availableCopies) => {
        bookService.editBook(id, name, category, author, availableCopies)
            .then(() => this.loadBooks());
    }

    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }
}

export default App;
