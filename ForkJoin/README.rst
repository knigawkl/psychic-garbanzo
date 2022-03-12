Zaimplementowano sekwencyjny oraz zrównoleglony algorytm sortowania ``Merge Sort``.
Porównania wydajności algorytmów dokonano przy pomocy profilera wbudowanego w ``Intellij IDEA``.

``ForkJoin`` to implementacja interfejsu ``ExecutorService`` przeznaczona do współbieżnego uruchomienia.
Dzięki zastosowaniu ``ForkJoin`` nie musimy się martwić niskopoziomowymi synchronizacjami i zamkami.
Framework ``ForkJoin`` jest algorytmem typu dziel i zwyciężaj, większe problemy są dzielone na mniejsze podproblemy,
a rozwiązania poszczególnych podproblemów są łączone w ostateczne rozwiązanie.
Aby było to możliwe, podproblemy muszą być oczywiście niezależne.

Wszystkie problemy, które chcemy zrównoleglić, powinne dziedziczyć po klasie ``RecursiveTask``,
jeśli coś zwracają (coś jest typem generycznym) lub ``RecursiveAction``, jeśli podproblemy nic nie zwracają.

``ForkJoinPool`` jest pulą wątków dla frameworka ``ForkJoin``.
``ForkJoinPool`` tworzy ustaloną liczbę wątków, zazwyczaj tyle, ile jest dostępnych rdzeni procesora.
Problemy są rozdzielane między tymi wątkami, jeśli dany wątek nie ma już zadań, to przekazywane są mu zadania bardziej
zapracowanego brata.

``ForkJoin`` jest złożony z operacji ``Fork`` oraz ``Join``.
``Fork`` odpowiada za dzielenie problemu na mniejsze podproblemy.
``Join`` wykonuje wydzielone podproblemy i łączy ich wyniki w całość.

``ForkJoin`` przypomina framework ``MapReduce``, stosowany w systemach BigData.
Podstawową różnicą jest fakt, że ``ForkJoin`` jest przeznaczony do działania na pojedynczej maszynie wirtualnej,
a ``MapReduce`` działa na klastrze maszyn. Frameworki porównano w artykule
``Comparing Fork/Join and MapReduce``, którego autorami są Robert Stewart i Jeremy Singer.

W zrównoleglonej implementacji sortowania przez scalanie wykorzystano ``RecursiveAction``.

Szczegółem implementacyjnym jest stwierdzenie, jakiej wielkości tablice mają być już sortowane, a nie dzielone na mniejsze.

