library(readxl)
data <- read_excel("H:/My Drive/Level_4/R language/Lab5/Ascent_STS121_Data.xlsx")

print(names(data))

colnames(data)[1:2] <- c("time", "altitude")

time <- data$time
altitude <- data$altitude

png("H:/My Drive/Level_4/R language/Lab5/time_vs_altitude.png")

plot(time, altitude,
     main = "Time vs Altitude",
     xlab = "Time",
     ylab = "Altitude",
     pch = 16)

dev.off()


#Functions

# Forward Divided Difference
student.numDiff.fdd <- function(time, altitude) {
  
  n <- length(time)
  result <- rep(NA, n)
  
  for (i in 1:(n-1)) {
    result[i] <- (altitude[i+1] - altitude[i]) / (time[i+1] - time[i])
  }
  
  return(result)

}

# Backward Divided Difference
student.numDiff.bdd <- function(time, altitude) {
  n <- length(time)
  result <- rep(NA, n)
  
  for (i in 2:n) {
    result[i] <- (altitude[i] - altitude[i-1]) / (time[i] - time[i-1])
  }
  
  return(result)
}

# Central Divided Difference
student.numDiff.cdd <- function(time, altitude) {
  n <- length(time)
  result <- rep(NA, n)
  
  for (i in 2:(n-1)) {
    result[i] <- (altitude[i+1] - altitude[i-1]) / (time[i+1] - time[i-1])
  }
  
  return(result)
}

# Second Derivative 
student.numDiff.secDer <- function(time, altitude) {
  n <- length(time)
  result <- rep(NA, n)
  
  for (i in 2:(n-1)) {
    h <- time[i] - time[i-1]
    result[i] <- (altitude[i+1] - 2*altitude[i] + altitude[i-1]) / (h^2)
  }
  
  return(result)
}

#Calculations
fdd <- student.numDiff.fdd(time, altitude)
bdd <- student.numDiff.bdd(time, altitude)
cdd <- student.numDiff.cdd(time, altitude)
acc <- student.numDiff.secDer(time, altitude)

png("H:/My Drive/Level_4/R language/Lab5/velocity_plot.png")

plot(time, fdd,
     col = "red",
     pch = 16,
     main = "Velocity vs Time",
     sub = "FDD (Red Circle), BDD (Blue Triangle), CDD (Green Cross)",
     xlab = "Time",
     ylab = "Velocity")

points(time, bdd, col = "blue", pch = 17)
points(time, cdd, col = "green", pch = 3)

dev.off()

png("H:/My Drive/Level_4/R language/Lab5/acceleration_plot.png")

plot(time, acc,
     col = "purple",
     pch = 16,
     main = "Acceleration vs Time",
     xlab = "Time (sec)",
     ylab = "Acceleration (m/s^2)")

dev.off()

