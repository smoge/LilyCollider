/*

    a = Fraction(5, 10);
    a.numer;
    a.denom;

    b = Fraction(3, 5);

    a + b

*/


Fraction {

    var <>numer, <>denom;

    *new { arg numerator, denominator;
        ^super.new.initFraction(numerator, denominator);
    }

    initFraction { arg thisNumerator, thisDenominator;

        var factor;
        (thisDenominator == 0).if(
            {// if denominator=0, both are 0
                this.numer_(0);
                this.denom_(0);
            }, {
                factor = gcd(thisNumerator.abs.asInteger, thisDenominator.abs.asInteger);
                (thisDenominator < 0).if({factor = factor.neg});
                this.numer_((thisNumerator/factor).abs);
                this.denom_((thisDenominator/factor).abs);
            })
    }


    + { arg other;

        ^Fraction(
            (this.numer * other.denom) + (this.denom * other.numer),
            this.denom * other.denom
        )
    }


    - { arg other;

        ^Fraction(
            (this.numer * other.denom) - (this.denom * other.numer),
            this.denom * other.denom
        )
    }


    * { arg other;

        ^Fraction(
            this.numer * other.numer,
            this.denom * other.denom
        )
    }


    / { arg other;

        ^Fraction(
            this.numer * other.denom,
            this.denom * other.numer
        )
    }



}