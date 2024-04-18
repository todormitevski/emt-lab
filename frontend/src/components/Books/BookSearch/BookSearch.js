import React, { Component } from "react";
import axios from "axios";

class BookSearch extends Component {
    state = {
        foundBooks: []
    };

    async componentDidMount() {
        const { data: foundBooks } = await axios.get(
            "http://localhost:8080/api/books"
        );
        this.setState({ foundBooks });
    }

    searchChanged = event => {
        this.setState({ search: event.target.value })
    }

    render() {
        return (
            <div>
                <input type='text' onChange={this.searchChanged} value={this.state.search}/>
                <div>
                    {this.state.foundBooks
                        .filter(book => book.name.includes(this.state.search))
                        .map(book => (
                                <ul key={book.id} className="list-group card card-1">
                                    <li className="list-group-item">{book.name}</li>
                                </ul>
                            )
                        )}
                </div>
            </div>
        );
    }
}

export default BookSearch;