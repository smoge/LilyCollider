+ SequenceableCollection {
    
	
    shake { arg deviation;
		
        ^this.collect({|i|
            i.gaussian(deviation)   
        })  
    }
	
	
    // interpolate two arrays
    interpolation { arg n, otherArray;

        ^Array.interpolation(n, this, otherArray )
    }

    // Already works:
    // [1, 2, 3, 4].gaussian
    // [1, 2, 3, 4].cauchy
    // [1, 2, 3, 4].logistic
    // [1, 2, 3, 4, 5].poisson + 1

    betarand {  arg val=1, prob1=1, prob2=1;
		
        ^this.collect({|i| 
            i.betarand(val, prob1, prob2)
        })
    }

    weibull { arg spread, shape=1;

        ^this.collect({|i| 
            i.weibull(spread, shape)
        })
    }
    
    // Usage:
    // [1, 2, 1, 1, [2, [1, 1, 1, 1,1]], 3, 1, 1].rotateTree(2)
    // -> [ 1, 1, 1, 2, [ 1, [ 1, 2, 1, 1, 1 ] ], 1, 1, 3 ]

    rotateStruct { arg nRotations = 1 ;
		
		var newStruct;
        newStruct = this.flat.rotate(nRotations).bubble;
        ^newStruct.reshapeLike(this);
        
    }

    
}