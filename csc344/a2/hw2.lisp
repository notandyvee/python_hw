;;;;Andy Valdez
;;;;Homework 2
;;;;3/19/2012



(defun andexp (a b) 
	(list 'and a b))
	
(defun orexp (a b) 
	(list 'or a b))
	
(defun notexp (a) 
	(list 'not a))

(setq p1 (andexp 'x (orexp 'x (andexp 'y (notexp 'z))))) ; x=nil z=1 simplifies to nil
(setq p2 (andexp (andexp 'z 1) (orexp 'x 1))) ; z = 1 x= nil simplfies to 1
(setq p3 (orexp (notexp 'z)(andexp 'z 1))) ; z = nil simplfies to nil

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defun evalexp (main-list bind-list)

(loop-binding main-list bind-list)
(print "Resulting expression after the binding")
(print main-list)
(print "The simplified version of your expression is:")
(simplify main-list)



	
);end of evalexp function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defun loop-binding (main-list bind-list)
  (compare main-list (car bind-list)) ;first just compare as I can make sure there is a binding list by checking in eval

(if (eq nil (atom (cdr bind-list)) ) (loop-binding main-list (cdr bind-list) )
  
);end of if


);end of loop-binding



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



(defun compare (main-list binding)

(if  (and (eq nil (cdr main-list)) (eq t (atom (car main-list))) )
 
     (if (eq (car main-list) (car binding)) (rplaca main-list (car(cdr binding)))) ;then this ** here you will compafre last car and be done

     (progn 

       (if (eq t (atom (car main-list) )) (if (eq (car main-list) (car binding)) (rplaca main-list (car(cdr binding)))) ) ;Here will check the car and if it is not a cons cell, compare it to the bind-list to substitute values

       (if (eq nil (atom (car main-list))) (compare (car main-list) binding)   ) ; Here checks if car is a cons, if it is, just recurse that first as to move from left to right of the list 

       (if (eq nil (atom (cdr main-list))) (compare (cdr main-list)binding) ) ;Here begin to check cdrs to see if it points to any cons, if yes, recurse that.


               ) ;else

     ) ;END OF IF
  

);end of compare function

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defun simplify (main-list)

  (if  (or  (and  (eq 'NOT(car main-list))  (eq t (atom (car(cdr main-list))) )  (eq nil (cdr(cdr main-list)))  )  
            (and (or (eq 'and(car main-list)) (eq 'or(car main-list))  )  (eq t (atom (car(cdr main-list))) )  (eq t (atom (car(cdr(cdr main-list)))))                  )) ;IF THIS

      (progn ;(print "the non-recursive part running")

       (if (eq 'and (car main-list)) ;if it equals and, follow these rules
        (if (or (eq nil (car (cdr main-list))) (eq nil (car(cdr(cdr main-list))))  )   (setq z nil)
            (if  (eq 1 (car (cdr main-list)))   (setq z (car(cdr(cdr main-list))))   
                (if   (eq 1 (car(cdr(cdr main-list)))) (setq z (car (cdr main-list)))))))
       

       (if (eq 'or (car main-list)) ;if it equals or follow these rules
           (if (or (eq 1 (car(cdr main-list))) (eq 1 (car(cdr(cdr main-list))))) (setq z 1) 
             (if (eq nil (car(cdr main-list)))  (setq z (car(cdr(cdr main-list)))) 
               (if (eq nil (car(cdr(cdr main-list)))) (setq z (car(cdr main-list)))))))
       

       (if (eq 'not (car main-list)) ;if equals not follow these rules
           (if (eq t (atom (car(cdr main-list))))
                (if (eq nil (car(cdr main-list))) (setq z 1) 
                    (if (eq 1 (car(cdr main-list))) (setq z nil)  
                     (setq z (car(cdr main-list))) 
                     )
                 )
             )
         )
       

      z) ;THEN THIS (HERE CHECK WHETHER ITS AND/OR/NOT AND SIMPLIFY BY FOLLOWING THE RULES



;;THE RECURSIVE PART

       (progn ;this part is the recursive parts where if it finds other expressions within expression it will recurse those to get the value
         ;(print "The recurive part is running")
         (if (eq 'not (car main-list))
                 (if (eq 1 (simplify (car(cdr main-list)))) (setq j nil)
                   (if (eq nil (simplify (car(cdr main-list)))) (setq j 1)
                         (setq j (simplify (car(cdr main-list)))) ) ) )


         (if (eq 'and (car main-list))
             (cond
              ( (and (eq nil (atom (car(cdr main-list))))  (eq t (atom (car(cdr(cdr main-list))))) )  
                    (if (or (eq nil (simplify (car(cdr main-list)))) (eq nil (car(cdr(cdr main-list))))  ) (setq j nil)
                      (if (eq 1 (simplify (car(cdr main-list)))) (setq j (car(cdr(cdr main-list)))) 
                          (if (eq 1 (car(cdr(cdr main-list)))) (setq j (car(cdr main-list)))  ) ) )  )

              ( (and (eq t (atom (car(cdr main-list))))  (eq nil (atom (car(cdr(cdr main-list)))))  )
                   (if  (or (eq nil (car(cdr main-list)))   (eq nil (simplify (car(cdr(cdr main-list)))))  ) (setq j nil)
                        (if (eq 1 (car(cdr main-list))) (setq j (simplify (car(cdr(cdr main-list)))))
                             (if (eq 1 (simplify (car(cdr(cdr main-list))))) (setq j (car(cdr main-list))) ) ) ) )

              ( (and  (eq nil (atom (car(cdr main-list))))  (eq nil (atom (car(cdr(cdr main-list)))))  ) 
                    (if (or (eq nil (simplify (car(cdr main-list)))) (eq nil (simplify (car(cdr(cdr main-list)))))  )  (setq j nil)  
                        (if (eq 1 (simplify (car(cdr main-list)))) (setq j (simplify (car(cdr(cdr main-list)))))
                             (if (eq 1 (simplify (car(cdr(cdr main-list)))))  (setq j (simplify (car(cdr main-list))))    ) ) ) ) ) )


              (if (eq 'or (car main-list))
                  (cond

                       ( (and (eq nil (atom (car(cdr main-list)))) (eq t (atom (car(cdr(cdr main-list))))) )
                             (if (or  (eq 1 (simplify (car(cdr main-list)))) (eq 1 (car(cdr(cdr main-list)))) ) (setq j 1) 
                                   (if (eq nil (simplify (car(cdr main-list)))) (setq j (car(cdr(cdr main-list)))) 
                                         (if (eq nil (car(cdr(cdr main-list)))) (setq j (simplify (car(cdr main-list)))) )
                                 )
                             )
                        )

                       ( (and   (eq t (atom (car(cdr main-list)))) (eq nil (atom (car(cdr(cdr main-list)))))  )
                             (if (or (eq 1 (car(cdr main-list))) (eq 1 (simplify (car(cdr(cdr main-list))))) ) (setq j 1)
                                 (if (eq nil (car(cdr main-list))) (setq j (simplify (car(cdr(cdr main-list)))))
                                       (if (eq nil (simplify (car(cdr(cdr main-list))))) (setq j (car(cdr main-list)))  )
                                 )
                             )
                        )

                       ( (and  (eq nil (atom (car(cdr main-list))))  (eq nil (atom (car(cdr(cdr main-list)))))  )
                             (if (or  (eq 1 (simplify (car(cdr main-list))))  (eq 1 (simplify (car(cdr(cdr main-list))))) ) (setq j 1)
                                   (if (eq nil (simplify (car(cdr main-list)))) (setq j (simplify (car(cdr(cdr main-list)))))
                                         (if (eq nil (simplify (car(cdr(cdr main-list)))))  (setq j (simplify (car(cdr main-list)))) )
                                )
                            )
                        )


                   )
               )

         j);ELSE THIS

       )    
               
);end of simplify function


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;







;;;;The following code snippet successfully changes a car of a cons
;;;;the function is : (rplaca x y) which changes the car of x to y and returns modified) x. x must be a cons
;;;;but y may be any Lisp object
;;;
;;;( rplaca  (cdr(car(cdr(cdr main-list))))  (car(cdr(car bind-list)))  )
;;;(print main-list)