(defun andexp (a b) 
	(list 'and a b))
	
(defun orexp (a b) 
	(list 'or a b))
	
(defun notexp (a) 
	(list 'not a))

(setq p1 (andexp 'x (orexp 'x (andexp 'y (notexp 'z))))) 
(setq p2 (andexp (andexp 'z 1) (orexp 'x 1)))
(setq p3 (orexp (notexp 'z)(andexp 'z 1)))


(defun evalexp (main-list bind-list)

(loop-binding main-list bind-list)
(print "Resulting expression after the binding")
(print main-list)
(print "The simplified version of your expression is:")
(simplify main-list)
	
)


(defun loop-binding (main-list bind-list)
  (compare main-list (car bind-list)) 

(if (eq nil (atom (cdr bind-list)) ) (loop-binding main-list (cdr bind-list) )
  
)


)


(defun compare (main-list binding)

(if  (and (eq nil (cdr main-list)) (eq t (atom (car main-list))) )
 
     (if (eq (car main-list) (car binding)) (rplaca main-list (car(cdr binding)))) ;then this ** here you will compafre last car and be done

     (progn 

       (if (eq t (atom (car main-list) )) (if (eq (car main-list) (car binding)) (rplaca main-list (car(cdr binding)))) ) 

       (if (eq nil (atom (car main-list))) (compare (car main-list) binding)   ) 

       (if (eq nil (atom (cdr main-list))) (compare (cdr main-list)binding) )


               )

     )
  

)

(defun simplify (main-list)

  (if  (or  (and  (eq 'NOT(car main-list))  (eq t (atom (car(cdr main-list))) )  (eq nil (cdr(cdr main-list)))  )  
            (and (or (eq 'and(car main-list)) (eq 'or(car main-list))  )  (eq t (atom (car(cdr main-list))) )  (eq t (atom (car(cdr(cdr main-list)))))                  ))

      (progn 

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
       

      z)



       (progn 
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

         j)

       )    
               
)
