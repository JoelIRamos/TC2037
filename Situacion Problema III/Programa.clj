(require '[clojure.string :as str])
(use 'clojure.java.io)

;; Automata Finito Deterministico
(def AFD
  '(;; Lista de transiciones
    (;; Formato: (estado-origen valor-simbolo estado-destino)
     (0 0 1) (0 2 2) (0 3 10) (0 4 11) (0 5 12) (0 6 12) (0 20 14) (0 21 14) (0 22 14) (0 7 15) (0 8 17) (0 9 18) (0 11 20) (0 12 109) (0 13 110) (0 14 111) (0 15 112) (0 16 113) (0 17 114) (0 18 116) (0 19 115) (0 23 117)
     (1 0 1) (1 1 3) (1 3 8) (1 13 100) (1 15 100) (1 17 100) (1 18 100) (1 19 100) (1 23 100)
     (2 0 1) (2 13 103) (2 15 103) (2 17 103) (2 18 103) (2 19 103) (2 23 103) (2 2 14) (2 4 14) (2 5 14) (2 6 14) (2 4 14) (2 20 14) (2 21 14) (2 22 14)
     (3 0 4)
     (4 0 4) (4 20 5) (4 13 101) (4 15 101) (4 17 101) (4 18 101) (4 19 101) (4 23 101)
     (5 0 7) (5 2 6)
     (6 0 7)
     (7 0 7) (7 13 101) (7 15 101) (7 17 101) (7 18 101) (7 19 101) (7 23 101)
     (8 0 9)
     (9 0 9) (9 13 102) (9 15 102) (9 17 102) (9 18 102) (9 19 102) (9 23 102)
     (10 13 103) (10 15 103) (10 17 103) (10 18 103) (10 19 103) (10 23 103)
     (11 13 103) (11 15 103) (11 17 103) (11 18 103) (11 19 103) (11 23 103) (11 2 14) (11 4 14) (11 5 14) (11 6 14) (11 20 14) (11 21 14) (11 22 14)
     (12 6 13) (12 13 103) (12 15 103) (12 17 103) (12 18 103) (12 19 103) (12 23 103) (12 2 14) (12 4 14) (12 5 14) (12 0 14) (12 20 14) (12 21 14) (12 22 14)
     (13 13 103) (13 15 103) (13 17 103) (13 18 103) (13 19 103) (13 23 103) (13 2 14) (13 4 14) (13 5 14) (13 6 14) (13 0 14) (13 20 14) (13 21 14) (13 22 14)
     (14 2 14) (14 4 14) (14 5 14) (14 6 14) (14 0 14) (14 20 14) (14 21 14) (14 22 14) (14 13 104) (14 15 104) (14 17 104) (14 18 104) (14 19 104) (14 23 104)
     (15 7 16)
     (16 18 105) (16 0 16) (16 1 16) (16 2 16) (16 3 16) (16 4 16) (16 5 16) (16 6 16) (16 7 16) (16 8 16) (16 9 16) (16 10 16) (16 11 16) (16 12 16) (16 13 16) (16 14 16) (16 15 16) (16 16 16) (16 17 16) (16 19 16) (16 23 16) (16 20 16) (16 21 16) (16 22 16)
     (17 0 106) (17 1 106) (17 2 106) (17 3 106) (17 4 106) (17 5 106) (17 6 106) (17 7 106) (17 8 106) (17 9 106) (17 10 106) (17 11 106) (17 12 106) (17 13 106) (17 14 106) (17 15 106) (17 16 106) (17 17 106) (17 20 106) (17 21 106) (17 22 106)
     (18 0 19) (18 1 19) (18 2 19) (18 3 19) (18 4 19) (18 5 19) (18 6 19) (18 7 19) (18 8 19) (18 9 107) (18 10 19) (18 11 19) (18 12 19) (18 13 19) (18 14 19) (18 15 19) (18 16 19) (18 17 19) (18 19 19) (18 23 19) (18 20 19) (18 21 19) (18 22 19)
     (19 0 19) (19 1 19) (19 2 19) (19 3 19) (19 4 19) (19 5 19) (19 6 19) (19 7 19) (19 8 19) (19 9 107) (19 10 19) (19 11 19) (19 12 19) (19 13 19) (19 14 19) (19 15 19) (19 16 19) (19 17 19) (19 19 19) (19 23 19) (19 20 19) (19 21 19) (19 22 19)
     (20 9 21)
     (21 0 22) (21 1 22) (21 2 22) (21 3 22) (21 4 22) (21 5 22) (21 6 22) (21 7 22) (21 8 22) (21 9 108) (21 10 22) (21 11 22) (21 12 22) (21 13 22) (21 14 22) (21 15 22) (21 16 22) (21 17 22) (21 19 22) (21 23 22) (21 20 22) (21 21 22) (21 22 22)
     (22 0 22) (22 1 22) (22 2 22) (22 3 22) (22 4 22) (22 5 22) (22 6 22) (22 7 22) (22 8 22) (22 9 108) (22 10 22) (22 11 22) (22 12 22) (22 13 22) (22 14 22) (22 15 22) (22 16 22) (22 17 22) (22 19 22) (22 23 22) (22 20 22) (22 21 22) (22 22 22))
    ;; Lista de estados finales
    (100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 116 117)))

;; Palabras Reservadas
(def palabras-reservadas '(("d" "e" "f") ("f" "n") ("d" "e" "f" "n")  ("l" "e" "t")
                                         ("d" "o") ("q" "u" "o" "t" "e") ("v" "a" "r")
                                         ("i" "f") ("t" "r" "y") ("l" "o" "o" "p") ("r" "e" "c" "u" "r")
                                         ("t" "h" "r" "o" "w") ("t" "r" "u" "e") ("f" "a" "l" "s" "e")
                                         ("a" "n" "d") ("n" "o" "t") ("o" "r") ("c" "o" "n" "d")
                                         ("m" "o" "n" "i" "t" "o" "r" "-" "e" "x" "i" "t")
                                         ("m" "o" "n" "i" "t" "o" "r" "-" "e" "n" "t" "e" "r")))


;; Funcion básica miembro
(defn member [atomo lista]
  (cond (nil? lista) false
        (= (first lista) atomo) true
        true (member atomo (next lista))))


;; Cortar el string en caracteres y convertirla en una lista de strings
;; Parametros: code - string con el codigo
;; Retorno: Lista con el codigo en caracteres en la lista de strings
;; Complejidad O(1)  
(defn trim-code [code]
  (apply list (str/split code #"")))


;; Agregar-palabra: Agregar el lexema al documento HTML
;; Parametros: lexema - acumulador del lexema
;;             archivo - archivo de salida
;; Complejidad: O(1)
(defn agregar-palabra [lexema archivo]
  (doall (map (fn [c] (spit archivo c :append true)) (reverse lexema))))


;; Agregar-estilo: Agregar las etiquetas span del lexema con su clase adecuada al docuento HTML
;; Parametros: lexema - acumulador del lexema
;;             tipo-lexema - clase del lexema
;;             archivo - archivo de salida
;; Complejidad: O(1)
(defn agregar-estilo [lexema tipo-lexema archivo]
  (spit archivo "<span class=\"" :append true)
  (spit archivo tipo-lexema :append true)
  (spit archivo "\">" :append true)
  (agregar-palabra lexema archivo)
  (spit archivo "</span>" :append true))


;; Switch de estado-final - tipo palabra
;; Parametros: estado-terminal - estado actual que ya fue verificado como terminal
;;             lexema - acumulador del lexema
;;             archivo - archivo de salida
;; Complejidad: O(1)
(defn imprimir-lexema [estado-terminal lexema archivo]
  (cond
    (= estado-terminal 100) (agregar-estilo lexema "Numero Ent" archivo)
    (= estado-terminal 101) (agregar-estilo lexema "Numero Dec" archivo)
    (= estado-terminal 102) (agregar-estilo lexema "Numero Prp" archivo)
    (= estado-terminal 103) (agregar-estilo lexema "Operador" archivo)
    (= estado-terminal 104) (if (member (reverse lexema) palabras-reservadas)
                              (agregar-estilo lexema "PalabraReservada" archivo)
                              (agregar-estilo lexema "Identificador" archivo))
    (= estado-terminal 105) (agregar-estilo lexema "Comentario" archivo)
    (= estado-terminal 106) (agregar-estilo lexema "Char" archivo)
    (= estado-terminal 107) (agregar-estilo lexema "String" archivo)
    (= estado-terminal 108) (agregar-estilo lexema "Regex" archivo)
    (= estado-terminal 109) (agregar-estilo lexema "Parentesis Abierto" archivo)
    (= estado-terminal 110) (agregar-estilo lexema "Parentesis Cerrado" archivo)
    (= estado-terminal 111) (agregar-estilo lexema "Corchete Abierto" archivo)
    (= estado-terminal 112) (agregar-estilo lexema "Corchete Cerrado" archivo)
    (= estado-terminal 113) (agregar-estilo lexema "Bracket Abierto" archivo)
    (= estado-terminal 114) (agregar-estilo lexema "Bracket Cerrado" archivo)
    (= estado-terminal 115) (spit archivo "&nbsp " :append true)
    (= estado-terminal 116) (spit archivo "<br>\n" :append true)
    (= estado-terminal 117) (spit archivo "&emsp ;" :append true)
    (= estado-terminal 120) (agregar-estilo lexema "Error" archivo)
    true (println "Error: estado terminal no existente")))


;; Encontrar el valor del caracter para la evaluacion en el automata
;; Parametros: c - caracter a buscarle su valor
;; Retorno: valor numerico del caracter
;; Complejidad: O(1)
(defn valor-simbolo [c]
  (cond
    ;; (char-numeric? c) 0
    (= c "0") 0
    (= c "1") 0
    (= c "2") 0
    (= c "3") 0
    (= c "4") 0
    (= c "5") 0
    (= c "6") 0
    (= c "7") 0
    (= c "8") 0
    (= c "9") 0
    (= c ".") 1
    (= c "-") 2
    (= c "/") 3
    (or (= c "+") (= c "*")) 4
    (or (= c ">") (= c "<")) 5
    (= c "=") 6
    (= c ";") 7
    (= c "\\") 8
    (= c "\"") 9
    (= c "'") 10
    (= c "#") 11
    (= c "(") 12
    (= c ")") 13
    (= c "[") 14
    (= c "]") 15
    (= c "{") 16
    (= c "}") 17
    (= c "\n") 18
    (= c " ") 19
    (= c "\t") 23
    (or (= c "e") (= c "E")) 20
    (string? c) 21 ;; string? char?
    true 22))



;; Buscar la transicion
;; Parametros: transicion - Seccion del AFD dond estan las transiciones
;;             estado - estado actual
;;             simbolo - caracter de entrada
;; Regreso: el siguiente estado de acuerdo con el AFD, faslo en caso de no existir
;; Complejidad: O(1)
(defn transicion [transiciones estado simbolo]
  (cond
    ;; (nil? transiciones) false 
    (nil? (first transiciones)) false
    (and (= (first (first transiciones)) estado) (= (first (next (first transiciones))) simbolo)) (first (next (next (first transiciones))))
    true (transicion (next transiciones) estado simbolo)))

(defn scanner [AFD estado codigo lexema archivo] nil)

;; De acuerdo con el siguiente estado, hacer la llamada recursiva correspondiente
;; Parametros: AFD - Automata Finito Deterministico
;;             estado - estado actual
;;             codigo - Lista con el codigo por caracter
;;             estado-siguiente: posible siguiente estado
;;             lexema - acumulador del lexema
;;             archivo - archivo de salida           
;; Complejidad: O(n)
(defn scanner-aux [AFD codigo estado-siguiente lexema archivo]
  (if (= estado-siguiente false)
    (do
      (imprimir-lexema 120 (cons (first codigo) lexema) archivo)
      (scanner AFD 0 (next codigo) nil archivo))
    (scanner AFD estado-siguiente (next codigo) (cons (first codigo) lexema) archivo)))


;; Recorrer lista
;; Parametros: AFD - AFD - Automata Finito Deterministico
;;             estado - estado actual
;;             codigo - Lista con el codigo por caracter
;;             lexema - acumulador del lexema
;;             archivo - archivo de salida
;; Retorno: Valor '#t' de que ha terminado la funcion
;; Complejidad: O(n)
(defn scanner [AFD estado codigo lexema archivo]
  (cond
    ;; (nil? codigo) true 
    (nil? (first codigo)) true
    (member estado (first (next AFD)))
    (if (> estado 105)
      (do
        (imprimir-lexema estado lexema archivo)
        (scanner AFD 0 codigo nil archivo)) ;; No regresion
      (do
        (imprimir-lexema estado (next lexema) archivo)
        (scanner AFD 0 (cons (first lexema) codigo) nil archivo)) ;; Regresion
      )
    true (scanner-aux AFD codigo (transicion (first AFD) estado (valor-simbolo (first codigo))) lexema archivo)))


;; O(n)
(defn resaltar-archivo [archE]
  ;; Iniciar el HTML del archivo
  (def archS (str archE ".html"))

  (spit archS "<html><head></head><body><p>")

  (scanner AFD 0 (trim-code (str (slurp archE) "\n\n")) nil archS)

  ;; Terminar el HTML (y CSS) del archivo
  (spit archS "</p><style>
  .PalabraReservada {
      color: purple;
      font-weight: bolder;
  }

  .Operador {
      color:rgb(245, 0, 0);
  }

  .Parentesis, .Corchete, .Bracket {
      color:black;
  }

  .Identificador {
      color: blue;
      font-weight:bold;
  }

  .Comentario {
      color: rgb(26, 122, 2);
      font-style: italic;
      font-weight: 500;
  }

  .Numero {
      color:rgb(32, 186, 224);
      font-weight: 400;
  }

  .Error {
      background-color: red;
  }

  .Char, .String {
      color: rgb(230, 156, 20);
  }

  .Regex {
      color: darkgoldenrod;
  }</style></body></html>" :append true))


;; (use 'clojure.java.io)
(defn leer-nombres [arch]
  (with-open [r (reader arch)]
    (doall (line-seq r))))

(defn multiples-archivos [arch]
  (doall (map resaltar-archivo (leer-nombres arch))))

(defn multiples-archivos [arch]
  (def nombres (leer-nombres arch))
  (def len (with-open [rdr (clojure.java.io/reader arch)] (count (line-seq rdr))))
  (doall (pmap resaltar-archivo (partition-all (Math/ceil (/ 100 len)) nombres))))