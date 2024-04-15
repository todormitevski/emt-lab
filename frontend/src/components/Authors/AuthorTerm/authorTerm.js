import React from 'react';

const authorTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.surname}</td>
        </tr>
    );
}

export default authorTerm;