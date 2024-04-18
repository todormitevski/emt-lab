import React from "react";
import ReactPaginate from "react-paginate";
import AuthorTerm from "../AuthorTerm/authorTerm";

class Authors extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 5
        }
    }

    getAuthorsPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.authors.map((term) => {
            return (
                <AuthorTerm term={term}/>
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        console.log(selected)
        this.setState({
            page: selected
        })
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.authors.length / this.state.size);
        const authors = this.getAuthorsPage(offset, nextPageOffset);

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Surname</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                authors
                            }
                            </tbody>
                        </table>
                        {/*<div className="col mb-3">*/}
                        {/*    <div className="row">*/}
                        {/*        <div className="col-sm-12 col-md-12">*/}
                        {/*            <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add book</Link>*/}
                        {/*        </div>*/}
                        {/*    </div>*/}
                        {/*</div>*/}
                    </div>
                </div>
                <ReactPaginate previousLabel={<button className="btn btn-primary">Previous</button>}
                               nextLabel={<button className="btn btn-primary">Next</button>}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1 p-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }
}

export default Authors;

// import React from 'react';
// import AuthorTerm from "../AuthorTerm/authorTerm";
//
// const authors = (props) => {
//     return (
//         <div className={"container mm-4 mt-5"}>
//             <div className={"row"}>
//                 <div className={"row"}>
//                     <table className={"table table-striped"}>
//                         <thead>
//                         <tr>
//                             <th scope={"col"}>Name</th>
//                             <th scope={"col"}>Surname</th>
//                         </tr>
//                         </thead>
//                         <tbody>
//                         {props.authors.map((term) => {
//                             return (
//                                 <AuthorTerm term={term}/>
//                             )
//                         })}
//                         </tbody>
//                     </table>
//                 </div>
//             </div>
//         </div>
//     );
// }
//
// export default authors;