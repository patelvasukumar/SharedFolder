f <- function(x) { x^(3/2) }

exact_value <- 2/5

student.trapezoid <- function(x0, xn, n){
  
  h <- (xn - x0) / n
  x <- seq(x0, xn, by = h)
  y <- f(x)
  
  approx <- (h / 2) * (y[1] + 2 * sum( y[2:n]) + y[n+1] )
  return(approx)
  
}

student.simpsons <- function(x0, xn, n){
  
  if(n == 2 || n %% 2 != 0){
    return(NA)
  }
  
  h <- (xn - x0) / n
  x <- seq(x0, xn, by = h)
  y <- f(x)
  
  odd_terms <- sum(y[seq(2, n ,by = 2)])
  even_terms <- sum(y[seq(3, n - 1, by = 2)])
  
  approx <- (h / 3) * (y[1] + y[n + 1] + 4 * odd_terms + 2 * even_terms)
  return(approx)
  
}

rel_error <- function(approx, exact) {
  abs(approx - exact) / abs(exact)
}

n_values <- 2^(1:9)

result <- data.frame(
  i = 1:length(n_values),
  n = n_values,
  Exact = exact_value,
  Trapezoid = NA,
  relErr = NA,
  `Simpson's` = NA,
  relErr2 = NA,
  check.names = FALSE
)

for (i in seq_along(n_values)) {
  n <- n_values[i]
  
  trap <- student.trapezoid(0, 1, n)
  simp <- student.simpsons(0, 1, n)
  
  result$Trapezoid[i] <- trap
  result$relErr[i] <- rel_error(trap, exact_value)
  
  result$`Simpson's`[i] <- simp
  result$relErr2[i] <- if (is.na(simp)) NA else rel_error(simp, exact_value)
}

names(result)[7] <- "relErr"

print(result)