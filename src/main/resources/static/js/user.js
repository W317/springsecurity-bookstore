document.addEventListener('DOMContentLoaded', () => {
    fetchBooks();
    fetchAuthors();
});

const booksTable = document.getElementById('booksTable').getElementsByTagName('tbody')[0];
const authorsTable = document.getElementById('authorsTable').getElementsByTagName('tbody')[0];

// Fetch and display books
function fetchBooks() {
    fetch('/api/books')
        .then(response => response.text())  // Change to response.text() to log raw response
        .then(text => {
            console.log('Raw response:', text); // Log the raw response
            return JSON.parse(text);           // Parse JSON if valid
        })
        .then(books => {
            booksTable.innerHTML = '';
            books.forEach(book => {
                const row = booksTable.insertRow();
                row.insertCell(0).textContent = book.id;
                row.insertCell(1).textContent = book.title;

                const authorsCell = row.insertCell(2);
                if (book.authors && book.authors.length > 0) {
                    authorsCell.textContent = book.authors.map(author => author.name).join(', ');
                } else {
                    authorsCell.textContent = 'Unknown';
                }

                row.insertCell(3).textContent = book.bookStatus;


                const actionCell = row.insertCell(4);
                const borrowButton = document.createElement('button');
                borrowButton.textContent = 'Borrow';
                borrowButton.addEventListener('click', () => borrowBook(book.id));
                borrowButton.classList.add('borrow-button');
                actionCell.appendChild(borrowButton);

                if (book.bookStatus !== "AVAILABLE") {
                    // Create Cancel Button
                    const cancelButton = document.createElement('button');
                    cancelButton.textContent = 'Cancel';
                    cancelButton.addEventListener('click', () => cancelBook(book.id));
                    cancelButton.classList.add('cancel-button');
                    actionCell.appendChild(cancelButton);
                }
            });
        })
        .catch(error => {
            console.error('Error fetching books:', error);
            // Handle the error, e.g., show an error message to the user
        });
}



// Fetch and display authors
function fetchAuthors() {
    fetch('/api/authors')
        .then(response => response.json())
        .then(authors => {
            authorsTable.innerHTML = '';
            authors.forEach(author => {
                const row = authorsTable.insertRow();
                row.insertCell(0).textContent = author.id;
                row.insertCell(1).textContent = author.name;
            });
        });
}

// Borrow book
function borrowBook(bookId) {
    fetch(`/api/books/borrow/${bookId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Book borrowed successfully!');
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
            fetchBooks();
        })
        .catch(error => alert('Error borrowing book: ' + error.message));
}

function cancelBook(bookId) {
    fetch(`/api/books/cancel/${bookId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (response.ok) {
                alert('Book cancelled borrow successfully!');
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
            fetchBooks();
        })
        .catch(error => alert('Error cancelling book: ' + error.message));
}