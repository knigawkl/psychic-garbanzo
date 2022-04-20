Zaimplementowano sekwencyjny oraz zrównoleglony algorytm sortowania ``Merge Sort``.
Porównania wydajności algorytmów dokonano przy pomocy profilera wbudowanego w ``Intellij IDEA``
(zastosowano ``Async Profiler`` oraz ``Java Flight Recorder``).

Sortowanie przez scalanie to rekurencyjny algorytm sortowania, stosujący metodę dziel i zwyciężaj.
Wyróżnić można 3 podstawowe kroki.

1. Podział zestawu danych na dwie równe części.
2. Zastosowanie sortowania przez scalanie dla każdej z nich oddzielnie, chyba że pozostał już tylko jeden element.
3. Połączenie posortowanych podciągów w jeden posortowany ciąg.

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

.. list-table:: Porównanie czasów sortowania algorytmem sekwencyjnym i zrównoleglonym w zależności od długości tablicy
   :widths: 25 25 50
   :header-rows: 1

   * - Długość tablicy
     - Czas sortowania algorytmem sekwencyjnym [ms]
     - Czas sortowania algorytmem zrównoleglonym [ms]
   * - 100000000
     - 11782
     - 5125
   * - 10000000
     - 1246
     - 597
   * - 1000000
     - 138
     - 106
   * - 100000
     - 17
     - 24
   * - 10000
     - 3
     - 6

Każdy prostokąt zawiera nazwę funkcji. Niebieskie prostokąty to natywne wywołania, a żółte to Javowe.

CPU samples - algorytm sekwencyjny

![Alt text](img/cpu-samples-seq.png)

CPU samples - algorytm zrównoleglony

![Alt text](img/cpu-samples-parallel.png)


