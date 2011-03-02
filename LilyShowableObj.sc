
LilyShowableObj {
	
	var <>fileName = "~/Desktop/LilyCollider";
	var <>pdfViewer = "okular"; // "evince"
	var <>textEditor = "frescobaldi"; // "jedit"
	
	
	*new {
		^super.new;
	}
	
	
	write {
		var file;
		
		file = File(this.fileName.standardizePath ++ ".ly","w");
		//         file.write(this.header ++ "\n");
		file.write(this.musicString);
		file.close;
	}
	
	
	plot {
		
		fork {
			
			this.write;
			0.1.wait;
			
			( "lilypond -o " ++ this.fileName.standardizePath ++ " " ++ this.fileName.standardizePath ++ ".ly"		).unixCmd { this.show};
			
		}
	}
	
	edit {
		this.write;
		(
			this.textEditor ++ " " ++ this.fileName.standardizePath ++ ".ly"
		).unixCmd;
	}
	
	
	show {
		
		(
			this.pdfViewer ++ " " ++ this.fileName.standardizePath ++ ".pdf"
		).unixCmd;
	}
	
}
