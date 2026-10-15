# Student Name: Vasukumar Patel
# Student Number: 041153940


# Differential Equation
f <- function(t, theta) {
  cos(2 * t) - theta
}


# True solution
true_theta <- function(t) {
  
    0.2 * cos(2 * t) + 0.4 * sin(2 * t) + 2.8 * exp(-t)

  }


# Created time grid that does not exceed t_end
make_time_grid <- function(t0, t_end, h) {
  
    t <- seq(t0, t_end, by = h)
    t[t <= t_end + 1e-12]

  }


# Percent relative error
percent_rel_error <- function(true_vals, approx_vals) {
  
    err <- ifelse(true_vals == 0, NA_real_, abs((true_vals - approx_vals) / true_vals) * 100)
    err[err == 0] <- NA_real_
    err

  }


# Euler's Method (RK1)
student.ode.euler <- function(t0, theta0, h, t_end = 3) {
  
    t <- make_time_grid(t0, t_end, h)
    n <- length(t)
  
    theta <- numeric(n)
    theta[1] <- theta0
  
    if (n > 1) {
      for (i in 1:(n - 1)) {
        theta[i + 1] <- theta[i] + h * f(t[i], theta[i])
      }
    }
  
    true_vals <- true_theta(t)
    error <- percent_rel_error(true_vals, theta)
  
    data.frame(
      time = t,
      approx = theta,
      true = true_vals,
      percent_error = error
    )

  }


# Runge-Kutta 4th Order Method
student.ode.rk4 <- function(t0, theta0, h, t_end = 3) {
  
    t <- make_time_grid(t0, t_end, h)
    n <- length(t)
  
    theta <- numeric(n)
    theta[1] <- theta0
  
    if (n > 1) {
      
        for (i in 1:(n - 1)) {
            k1 <- f(t[i], theta[i])
            k2 <- f(t[i] + h / 2, theta[i] + h * k1 / 2)
            k3 <- f(t[i] + h / 2, theta[i] + h * k2 / 2)
            k4 <- f(t[i] + h, theta[i] + h * k3)
      
            theta[i + 1] <- theta[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4)
    
            }
  
      }
  
    true_vals <- true_theta(t)
    error <- percent_rel_error(true_vals, theta)
  
    data.frame(
      time = t,
      approx = theta,
      true = true_vals,
      percent_error = error
    )

  }


# Inputs
theta0 <- 3
t0 <- 0


# Generate data
euler_h1 <- student.ode.euler(t0, theta0, 0.8)
euler_h2 <- student.ode.euler(t0, theta0, 0.2)
euler_h3 <- student.ode.euler(t0, theta0, 0.05)

rk4_h1 <- student.ode.rk4(t0, theta0, 0.8)
rk4_h2 <- student.ode.rk4(t0, theta0, 0.2)
rk4_h3 <- student.ode.rk4(t0, theta0, 0.05)


# Print data frames
print(euler_h1)
print(euler_h2)
print(euler_h3)

print(rk4_h1)
print(rk4_h2)
print(rk4_h3)


# Common axis ranges for both graphs
all_times <- sort(unique(c(
  euler_h1$time, euler_h2$time, euler_h3$time,
  rk4_h1$time, rk4_h2$time, rk4_h3$time
)))

all_y <- c(
  euler_h1$approx, euler_h2$approx, euler_h3$approx,
  rk4_h1$approx, rk4_h2$approx, rk4_h3$approx,
  true_theta(all_times)
)

x_lim <- c(0, 3)
y_lim <- range(all_y, na.rm = TRUE)


# Euler plot
png("Euler_Method.png", width = 1200, height = 800, res = 150)

plot(euler_h1$time, euler_h1$approx,
     col = "red", pch = 1,
     xlim = x_lim, ylim = y_lim,
     xlab = "Time (sec)",
     ylab = expression(theta(t)),
     main = "Euler Method",
     sub = "h = 0.8 (red circle), 0.2 (blue triangle), 0.05 (green cross), True (brown x)")

points(euler_h2$time, euler_h2$approx, col = "blue", pch = 2)
points(euler_h3$time, euler_h3$approx, col = "green", pch = 3)
points(all_times, true_theta(all_times), col = "brown", pch = 4)

dev.off()


# RK4 plot
png("RK4_Method.png", width = 1200, height = 800, res = 150)

plot(rk4_h1$time, rk4_h1$approx,
     col = "red", pch = 1,
     xlim = x_lim, ylim = y_lim,
     xlab = "Time (sec)",
     ylab = expression(theta(t)),
     main = "Runge-Kutta 4th Order Method",
     sub = "h = 0.8 (red circle), 0.2 (blue triangle), 0.05 (green cross), True (brown x)")

points(rk4_h2$time, rk4_h2$approx, col = "blue", pch = 2)
points(rk4_h3$time, rk4_h3$approx, col = "green", pch = 3)
points(all_times, true_theta(all_times), col = "brown", pch = 4)

dev.off()

