# STUDENT NAME : VASUKUMAR PATEL
# STUDENT NUMBER : 041153940

#PART - 1

data("airquality")
my_df <- airquality

airquality

str(my_df)
head(my_df,6)

names(my_df)

my_df_temp <- data.frame( temp = c(my_df$Temp))
print(my_df_temp)

length( unique(my_df$Month) )

mean_temp <- tapply(my_df$Temp, my_df$Month, mean)
mean_temp

median_temp <- tapply(my_df$Temp, my_df$Month, median)
median_temp

sd_temp <- tapply(my_df$Temp, my_df$Month, sd)
sd_temp

overall_mean <- mean(my_df$Temp)
overall_mean

overall_median <- median(my_df$Temp)
overall_median

overall_sd <- sd(my_df$Temp)
overall_sd

#PART - 2 

hist(my_df$Temp,
     main = "Histogram of Daily Temperatures",
     xlab = "Temperature (°F)",
     ylab = "Frequency")

boxplot(Ozone ~ Month, data = my_df,
        main = "Boxplot of Ozone by Month",
        xlab = "Month",
        ylab = "Ozone")

plot(my_df$Temp, my_df$Ozone,
     main = "Scatterplot of Temperature vs. Ozone",
     xlab = "Temperature (°F)",
     ylab = "Ozone" )
     
abline(lm(Ozone ~ Temp, data = my_df), col = "blue", lwd = 2)

#PART - 4

prob_less_70 <- pnorm(70, mean = overall_mean, sd = overall_sd)
prob_less_70

prob_greater_85 <- 1 - pnorm(85, mean = overall_mean, sd = overall_sd)

prob_75_90 <- pnorm(90, mean = overall_mean, sd = overall_sd) - 
            pnorm(75, mean = overall_mean, sd = overall_sd)
prob_75_90

mean.Temp.All <- mean(my_df$Temp) 
sd.Temp.All   <- sd(my_df$Temp) 

my_df$z_score <- (my_df$Temp - mean.Temp.All) / sd.Temp.All

head(my_df)

my_df

my_df[102, ]