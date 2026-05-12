# SI_2026_lab2_236032

## Luka Apostolov 236032

### Control Flow Graph

![searchBookByTitle CFG](searchBookByTitle.png)
![borrowBook CFG](borrowBook.png)

### Цикломатска комплексност

Цикломатската комплексност на функцијата `searchBookByTitle` е 4, пресметана преку формулата V(G) = E - N + 2 = 12 - 10 + 2 = 4. Функцијата има 4 одлучувачки точки: `if(title.isEmpty())`, `for` јамката, `if(title matches && !isBorrowed())` и `if(results.isEmpty())`.

Цикломатската комплексност на функцијата `borrowBook` е 5, пресметана преку формулата V(G) = E - N + 2 = 13 - 10 + 2 = 5. Функцијата има 4 одлучувачки точки: `if(title.isEmpty() || author.isEmpty())`, `for` јамката, `if(title matches && author matches)` и `if(!isBorrowed())`.

### Тест случаи според критериумот Every Statement

Треба да има минимално 3 тест случаи за да се постигне критериумот Every Statement за функцијата `searchBookByTitle`.

Test 1: title="" - throws IllegalArgumentException - покрива: `if(title.isEmpty())` → throw

Test 2: title="Clean Code" (постои) - враќа листа - покрива: `new ArrayList()`, `for` јамка, `if(matches && !isBorrowed())`, `results.add()`, `return results`

Test 3: title="NonExistent" (не постои) - враќа null - покрива: `if(results.isEmpty())` → `return null`

### Тест случаи според критериумот Every Branch

Треба да има минимално 5 тест случаи за да се постигне критериумот Every Branch за функцијата `borrowBook`.

Test 1: title="" - throws IllegalArgumentException - гранка: `if(isEmpty||isEmpty)` = true (title празен)

Test 2: title="The Hobbit", author="" - throws IllegalArgumentException - гранка: `if(isEmpty||isEmpty)` = true (author празен)

Test 3: title="Unknown", author="Author" - throws RuntimeException "Book not found" - гранка: for јамката завршува без match

Test 4: title="The Hobbit", author="Tolkien" (достапна) - позајмува успешно - гранка: `if(matches)` = true, `if(!isBorrowed)` = true

Test 5: title="The Hobbit", author="Tolkien" (веќе позајмена) - throws RuntimeException - гранка: `if(!isBorrowed)` = false

### Тест случаи според критериумот Multiple Condition

Треба да има минимално 4 тест случаи за да се постигне критериумот Multiple Condition.

За условот `if (book.getTitle().equalsIgnoreCase(title) && !book.isBorrowed())` во `searchBookByTitle`:

Test 1: titleMatches=T, !isBorrowed=T → книгата се додава во резултатите

Test 2: titleMatches=T, !isBorrowed=F → книгата не се додава

Test 3: titleMatches=F, !isBorrowed=F → книгата не се додава

Test 4: titleMatches=F, !isBorrowed=T → книгата не се додава

За условот `if (title.isEmpty() || author.isEmpty())` во `borrowBook`:

Test 1: title.isEmpty()=T, author.isEmpty()=T → throws IllegalArgumentException

Test 2: title.isEmpty()=T, author.isEmpty()=F → throws IllegalArgumentException

Test 3: title.isEmpty()=F, author.isEmpty()=T → throws IllegalArgumentException

Test 4: title.isEmpty()=F, author.isEmpty()=F → продолжува со извршување

### Објаснување на напишаните unit tests

```java
@Test
void searchBookEveryStatementTest() {
    Library lib = new Library();
    assertThrows(IllegalArgumentException.class, () ->
            lib.searchBookByTitle("")
    );
    lib.addBook(new Book("Clean Code", "Robert Martin", "IT"));
    assertNotNull(lib.searchBookByTitle("Clean Code"));
    assertNull(lib.searchBookByTitle("NonExistent"));
}

@Test
void borrowBookEveryBranchTest() {
    Library lib = new Library();
    Book book = new Book("The Hobbit", "Tolkien", "Fantasy");
    lib.addBook(book);
    assertThrows(IllegalArgumentException.class, () ->
            lib.borrowBook("", "Tolkien")
    );
    assertThrows(IllegalArgumentException.class, () ->
            lib.borrowBook("The Hobbit", "")
    );
    assertThrows(RuntimeException.class, () ->
            lib.borrowBook("Unknown", "Author")
    );
    lib.borrowBook("The Hobbit", "Tolkien");
    assertTrue(book.isBorrowed());
    assertThrows(RuntimeException.class, () ->
            lib.borrowBook("The Hobbit", "Tolkien")
    );
}

@Test
void searchBookMultipleConditionTest() {
    Library lib = new Library();
    Book b1 = new Book("Clean Code", "Author", "IT");
    Book b2 = new Book("Clean Code", "Author", "IT");
    b2.setBorrowed(true);
    lib.addBook(b1);
    lib.addBook(b2);
    assertNotNull(lib.searchBookByTitle("Clean Code"));
    b1.setBorrowed(true);
    assertNull(lib.searchBookByTitle("Clean Code"));
    assertNull(lib.searchBookByTitle("Unknown"));
    assertNull(lib.searchBookByTitle("Another"));
}

@Test
void borrowBookMultipleConditionTest() {
    Library lib = new Library();
    lib.addBook(new Book("Test", "Author", "Genre"));
    assertThrows(IllegalArgumentException.class, () ->
            lib.borrowBook("", "")
    );
    assertThrows(IllegalArgumentException.class, () ->
            lib.borrowBook("", "Author")
    );
    assertThrows(IllegalArgumentException.class, () ->
            lib.borrowBook("Test", "")
    );
    lib.borrowBook("Test", "Author");
}
```

Во тестовите користам `assertThrows` за да ги фатам exceptions-ите кои ги фрла функцијата и да проверам дали се токму тие кои ги очекуваме. За успешните случаи користам `assertNotNull`, `assertNull` и `assertTrue` за да го проверам точниот резултат.