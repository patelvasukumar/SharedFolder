#Lab 5

n <- 1.0
x<-1.0
i<-0
cat(paste("i", "x", "n\n", sep="\t\t"))
while (i < 1025) {
  n<-n*2.0
  x<-1.0/n

  if (i > 1000){
    
    if (x != 0){
      cat(paste( i, x, n, "\n", sep="\t"))
    }else{
      cat(paste( i, x, n, "\n", sep="\t\t"))
    }
  }

  
  i<-i+1
}
