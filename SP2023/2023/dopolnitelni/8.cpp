#include <iostream>
#include <cstring>

int checkUp(char matrica[100][100], char zbor[16], int row, int col) {
    for (int i = 0; i < strlen(zbor); i++) {
        if (matrica[row - i][col] != zbor[i]) {
            return 0;
        }
    }

    return 1;
}

int checkDown(char matrica[100][100], char zbor[16], int row, int col) {
    for (int i = 0; i < strlen(zbor); i++) {
        if (matrica[row + i][col] != zbor[i]) {
            return 0;
        }
    }

    return 1;
}

int checkRight(char matrica[100][100], char zbor[16], int row, int col) {
    for (int i = 0; i < strlen(zbor); i++) {
        if (matrica[row][col + i] != zbor[i]) {
            return 0;
        }
    }
    return 1;
}

int checkLeft(char matrica[100][100], char zbor[16], int row, int col) {
    for (int i = 0; i < strlen(zbor); i++) {
        if (matrica[row][col - i] != zbor[i]) {
            return 0;
        }
    }
    return 1;
}

int check(char matrica[100][100], char zbor[16], int i, int j) {
    if (checkRight(matrica, zbor, i, j)) {
        std::cout << i << ", " << j << " -> " << i << ", " << j + strlen(zbor) - 1;
        return 1;
    } else if (checkLeft(matrica, zbor, i, j)) {
        std::cout << i << ", " << j << " -> " << i << ", " << j - strlen(zbor) + 1;
        return 1;
    } else if (checkUp(matrica, zbor, i, j)) {
        std::cout << i << ", " << j << " -> " << i - strlen(zbor) + 1 << ", " << j;
        return 1;
    } else if (checkDown(matrica, zbor, i, j)) {
        std::cout << i << ", " << j << " -> " << i + strlen(zbor) - 1 << ", " << j;
        return 1;
    }
    return 0;
}

int main() {
    int n;
    std::cin >> n;

    char matrica[100][100];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            std::cin >> matrica[i][j];
        }
    }

    char zbor[16];
    std::cin >> zbor;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (check(matrica, zbor, i, j)) {
                return 0;
            }
        }
    }

    std::cout << "Not Found";

    return 0;
}