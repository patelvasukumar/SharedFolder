myFun <- function(Vec1){
  
  result <- numeric(length(Vec1))
  
  result[Vec1 < 0] <- Vec1[Vec1 < 0]^2 + 2 * Vec1[Vec1 < 0] + 3
  
  result[Vec1 >= 0 & Vec1 < 2] <- Vec1[Vec1 >= 0 & Vec1 < 2] + 3 
  
  result[Vec1 >= 2] <- Vec1[Vec1 >= 2]^2 + 4 * Vec1[Vec1 >= 2] - 7
  
  return(result)
  
}

x <- seq(-4, 3)
y <- myFun(x)


plot(y, type = "l")
