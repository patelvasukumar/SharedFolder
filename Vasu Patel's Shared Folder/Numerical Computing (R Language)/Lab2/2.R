v <- 1:25
total <- 0

for (i in v) {
  total <- total + ( (2^i/i) + (3^i/i^2) )
}

cat("The sum of this summation is:",total)
