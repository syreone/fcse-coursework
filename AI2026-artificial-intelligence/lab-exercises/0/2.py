def minesweeper(board):
    n = len(board)
    return [
        [
            '#' if board[r][c] == '#' else str(sum(
                board[r+dr][c+dc] == '#'
                for dr in [-1, 0, 1]
                for dc in [-1, 0, 1]
                if (dr != 0 or dc != 0) and 0 <= r+dr < n and 0 <= c+dc < n
            ))
            for c in range(n)
        ]
        for r in range(n)
    ]

n = int(input())
board = [input().split('   ') for _ in range(n)]

result = minesweeper(board)
for row in result:
    print('   '.join(row))