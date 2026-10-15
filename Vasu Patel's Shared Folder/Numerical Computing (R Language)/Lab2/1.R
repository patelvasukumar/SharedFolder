myFunction <- function() {
  
  x <- seq(3, 6, by = 0.1)
  y <- exp(x)
  
  cVec <- 0.1 * y * cos(x) + 2 * log(abs(x))
  
  total <- sum(cVec)
  
  cat(sprintf("The sum of this vector is: %.4f\n", total))
  
  plot(cVec, main = "My First Plot")
}

myFunction()
