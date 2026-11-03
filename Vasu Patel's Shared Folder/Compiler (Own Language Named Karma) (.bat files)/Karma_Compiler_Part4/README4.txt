@sunya Karma Language by Vasu and Kavya @
@sunya TCL-style inspired  @
@sunya README2.txt @

intK mainK() {

    intK radiusK;
    floatK areaK;

    printK('Program started');

    radiusK = 5;

    areaK = 3.14159 * radiusK * radiusK;

    printK('Radius is: ');
    printK(radiusK);

    printK('Area of circle (pi r square) is: ');
    printK(areaK);

    if { areaK > 50 } {
        printK(' अच्छा खासा बड़ा सर्कल है ');
    }
    else {
        printK(' छोटा सा सर्कल है ');
    }

    moksha;

}