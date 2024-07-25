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

                const statusCell = row.insertCell(3)
                if (book.is_borrowed === true) {
                    statusCell.textContent = "borrowed";
                } else {
                    statusCell.textContent = "not be borrowed"
                }

                const actionCell = row.insertCell(4);
                const addButton = document.createElement('button');
                addButton.textContent = 'Borrow Book';
                // addButton.addEventListener('click', () => toggleForm('bookForm'));
                actionCell.appendChild(addButton);
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