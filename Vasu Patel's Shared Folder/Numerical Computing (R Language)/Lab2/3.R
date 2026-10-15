set.seed(75)

Vec1 <- sample(0:999, size = 100, replace = TRUE)
Vec2 <- sample(0:999, size = 100, replace = TRUE)

Vec2a <- Vec2[Vec2 > 600]
Vec2b <- which(Vec2 > 600)
Vec1c <- Vec1[Vec2 > 600]
divByTwo <- sum(Vec1 %% 2 == 0)


print(Vec1)
print(Vec2)
print(Vec2a)
print(Vec2b)
print(Vec1c)
print(divByTwo)
