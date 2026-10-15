taylor <- function(x, numbers){
  
  approximateValue <- 0
  
  for (k in 0:(numbers)) {
    approximateValue <- approximateValue + ((-1)^k) * (x-1)^k
  }
  
  trueValue <- 1 / x
  absoluteError <- abs(trueValue - approximateValue)
  
  relativeError <- absoluteError / abs(trueValue)
  
  cat("x value:", x, "\n")
  cat("n value:", numbers, "\n\n")
  
  cat("True value:        ", trueValue, "\n")
  cat("Approximate value: ", approximateValue, "\n")
  cat("Absolute error:    ", absoluteError, "\n")
  cat("Relative error:    ", relativeError, "\n\n")
  
}

taylor(0.1,10)
taylor(0.1,100)

taylor(1.75,10)
taylor(1.75,100)

taylor(2.1,10)
taylor(2.1,100)

taylor(3.3,10)
taylor(3.3,100)
