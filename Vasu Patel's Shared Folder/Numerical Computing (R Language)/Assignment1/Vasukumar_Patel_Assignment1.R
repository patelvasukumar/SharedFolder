
# Student Name : Vasukumar Patel
# Student Number : 041153940
# Assignment 1 - R Language - Part - B code

# PART - B 

n <- 1:10
factorial_n <- factorial(n)
approximation <- ( (n / exp(1))^n * sqrt(2*pi*n) )
absolute_error <- abs(factorial_n - approximation)
percent_relative_error <- (absolute_error/factorial_n) * 100

df <- data.frame(
  n = n,
  factorial_n = signif(factorial_n,2),
  Approximation = signif(approximation, 2),
  absolute_error = signif(absolute_error, 2),
  percent_relative_error = signif(percent_relative_error, 2)
)

df

# PART- C 

data("mtcars")
names(mtcars)
head(mtcars)
str(mtcars) 
summary(mtcars) 

mtcars$vs <- factor(mtcars$vs,
                    levels = c(0, 1),
                    labels = c("V-Shaped", "Straight"))
str(mtcars$vs)
table(mtcars$vs)
head(mtcars)

plot( mtcars$mpg, mtcars$hp,
      main = "Horsepower vs Miles Per Gallon",
      xlab = "Miles Per Gallon",
      ylab = "Horsepower" )

red_cars <- mtcars[ (mtcars$hp < 250 & mtcars$hp > 200) & mtcars$mpg < 13, ]
rownames(red_cars)

green_cars <- mtcars[mtcars$hp < 100 & mtcars$mpg > 30, ]
rownames(green_cars)

red_cars
green_cars




