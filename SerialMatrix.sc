/*

    Description: Creates a matrix with all possible serial combinations

    USAGE:

    a = SerialMatrix.new([1, 2, 3, 4, 5])
    a.gui
    a.org(0)
    a.inv(5)
    a.rev(10)
    a.invRev
    a.revInv
    a.matrix
    a.size
    a.asMatrix
    a.at(3, 2)

*/

SerialMatrix {

    var <>list, <size, <inversion, <transpositions, <matrix;

    *new { arg  list;
        ^super.new.init(list);
    }

    init { arg thisSeq;

        list = thisSeq;
        size = list.size;
        inversion = Array.fill(size,
            {|i|
                (list[0]-(list[i]-list[0]))%size
            });
        transpositions = inversion-list[0]%size;
        matrix = Array.fill(size, {|i| (list + transpositions[i])%size});
    }

    asMatrix {
        ^Matrix.with(matrix)
    }

    at { arg thisRow, thisCol;
        ^this.asMatrix.at(thisRow, thisCol)
    }

    gui {
        var w, numberBoxes;
        w=Window("SerialMatrix",Rect(261, 206, 100 + (size*23) , 100 + (size*23))).front;

        numberBoxes = Array.fill(size,{|j|
            Array.fill(size, {|i|
                NumberBox(w,Rect(10+(i*25),10+(j*25),20,20))
            });
        });

        size.do({|i|
            size.do({|j|
                numberBoxes[i][j].value = matrix[i][j];

            })
        });

        w.front;

    }

    post {
        matrix.postnl;
    }

    org {arg number=0;
        number = number.round.abs%size;
        ^matrix[number];
    }

    rev {arg number=0;
        number = number.round.abs%size;
        ^matrix[number].reverse;
    }

    inv {arg number=0;
        number = number.round.abs%size;
        ^matrix.flop[number];
    }

    invRev {arg number=0;
        number = number.round.abs%size;
        ^matrix.flop[number].reverse;
    }

    revInv {arg number=0;
        number = number.round.abs%size;
        ^matrix.flop[number].reverse;
    }

}