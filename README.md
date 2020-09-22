# simian
Projeto que detecta se uma sequência de DNA pertence a um humano ou a um símio

APIs disponíveis

1) POST → /simian 

Esse serviço recebe uma sequência de DNA através de um HTTP POST com um JSON que contém o seguinte formato, exemplo: 


 POST → /simian 
  
 { "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] } 
 
  
 2) GET -> /stats
 
 A resposta deve ser um Json que retorna as estatísticas de verificações de DNA, onde deve informar a 
 quantidade de DNA’s símios, quantidade de DNA’s humanos, e a proporção de símios para a população humana.  
 
 Segue exemplo da resposta: 
 
{"count_mutant_dna": 40, "count_human_dna": 100: "ratio": 0.4} 
 
