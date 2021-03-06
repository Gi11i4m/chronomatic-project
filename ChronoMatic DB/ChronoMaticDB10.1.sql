SELECT s.SPELERSNR, NAAM, SUM(BEDRAG) as TOTAAL_BOETEBEDRAG
FROM spelers as s INNER JOIN teams as t
	ON s.SPELERSNR = t.SPELERSNR
	LEFT OUTER JOIN boetes as b
		ON s.SPELERSNR = b.SPELERSNR
GROUP BY SPELERSNR
HAVING COUNT(t.TEAMNR) >= 1