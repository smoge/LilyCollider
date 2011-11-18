
\header{
  tagline = ""
}


\paper {
  between-system-padding = #15
  bottom-margin = 0.75\in
  left-margin = 0.75\in
  line-width = 6.75\in
}


#(set-default-paper-size "letter")
#(set-global-staff-size 14)

\midi { }

\layout {

  indent = #0
  ragged-right = ##t

  \context {
    \Score

    % proportional notation
    % proportionalNotationDuration = #(ly:make-moment 1 32)
    \override SpacingSpanner #'strict-note-spacing = ##t
    \override SpacingSpanner #'strict-grace-spacing = ##t
    \override SpacingSpanner #'uniform-stretching = ##t

    % bar numbers
    %\remove Bar_number_engraver

    % bar lines
    %\remove Default_bar_line_engraver

    % markup overrides
    \override TextScript #'staff-padding = #4
    \override TimeSignature #'transparent = ##t

  }

  \context {
    \Voice
    %\remove "Pitch_squash_engraver"
    %\remove "Stem_engraver"
  }

}
