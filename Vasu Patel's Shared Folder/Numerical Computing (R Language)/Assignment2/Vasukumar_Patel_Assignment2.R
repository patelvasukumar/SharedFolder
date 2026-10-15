# Student Name: Vasukumar Patel
# Assignment 2

library(readxl)
TransistorCount1974_2020 <- read_excel("H:/My Drive/Level_4/R language/Assignment2/TransistorCount1974_2020.xlsx")
View(TransistorCount1974_2020)


data <- TransistorCount1974_2020
colnames(data) <- c("CPU", "TransistorCount", "Year")


data$year_new <- data$Year - 1970
data$log_transistor_count <- log(data$TransistorCount)
data$residuals <- NA


#PART-A

model <- lm(TransistorCount ~ year_new, data = data)

coef(model)

plot(data$year_new, data$TransistorCount,
     xlab = "Year (since 1970)",
     ylab = "Transistor Count",
     main = "Transistor Count vs Year")

abline(model, col = "red")

#PART-B 

model_log <- lm(log_transistor_count ~ year_new, data = data)

data$residuals <- residuals(model_log)

outliers <- data[order(-abs(data$residuals)), 
                 c("CPU", "Year", "TransistorCount", "residuals")][1:3, ]

outliers

coef(model_log)

plot(data$year_new, data$log_transistor_count,
     xlab = "Year (since 1970)",
     ylab = "Log(Transistor Count)",
     main = "Log(Transistor Count) vs Year")

abline(model_log, col = "blue")

#PART-C 

b0 <- coef(model_log)[1]
b1 <- coef(model_log)[2]

year_2022 <- 2022 - 1970

predicted_value <- exp(b0 + b1 * year_2022)

predicted_value

values_2022 <- c(16000000000,114000000000,26000000000,
                 45000000000,20000000000,17000000000,
                 16000000000,90000000000)

actual_average <- mean(values_2022)

actual_average

#Relative Errors

difference <- abs(predicted_value - actual_average)
difference

percentage_error <- (difference / actual_average) * 100
percentage_error
