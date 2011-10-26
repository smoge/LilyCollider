
LilyShowableObj : LilyObj {

    var <>fileName = "~/Desktop/sketch"; 
	var <>pdfViewer = "xpdf -remote sclyserver";
	var <>midiPlayer = "vlc"; 
	var <>textEditor = "frescobaldi"; 
	var <>template = "doc"; 
	var <>lilyCmd= "lilypond";

    *new {
        ^super.new;
    }


    /* Music expression between curly brackets */
    musicString {

        ^("{\n" ++  this.string ++ "\n}\n").asString;
    }


    /* Path of the choosen template LilyPond file */
    templateFile {
        ^(this.templatesFolder ++ "/" ++ this.template ++ ".ly").standardizePath
    }


    header {
        var file, content;

        file = File(this.templateFile,"r");
        content = file.readAllString;
        file.close;
        ^content;
    }


    /* Write the File to Disk */
    write {
        var file;

        file = File(this.fileName.standardizePath ++ ".ly","w");
        file.write(this.header);
        file.write(this.musicString);
        file.close;
    }


    /* Array of the available LilyPond templates Paths */
    templatePathList {

        ^(templatesFolder ++ "/*").pathMatch
    }


    /* Array of the available LilyPond templates Names */
    templateList {

        ^(this.templatePathList.collect {|i| i.basename})

    }


    /* Call the PDF Viewer to show the produced PDF File: */
    show { (this.pdfViewer ++ " " ++ this.fileName.standardizePath ++ ".pdf" ).unixCmd }


    /* Call the MIDI player program */
    playMidi { ( this.midiPlayer ++ " " ++ this.fileName.standardizePath ++ ".midi" ).unixCmd }


    /* Produce the score with LilyPond, when it's done open with the PDF viewer */
    plot {

        fork {
            this.write;
            0.1.wait;
            (
				this.lilyCmd ++ " -o " ++ this.fileName.standardizePath ++ " " ++
                this.fileName.standardizePath ++ ".ly"
            ).unixCmd { this.show };
        }
    }


    /* Call the text editor to open the .ly file */
    edit {
        this.write;
        (this.textEditor ++ " " ++ this.fileName.standardizePath ++ ".ly").unixCmd;
    }

}
