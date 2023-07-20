ALTER TABLE adquisicion ADD COLUMN orden int(4);

set @row_number := 0;

Update
  adquisicion as adq
  inner join (
	SELECT 
	    @row_number:=CASE
	        WHEN @adq_no = adq_pre_fk 
				THEN @row_number + 1
	        ELSE 1
	    END AS num,
	    @adq_no:=adq_pre_fk adq_pre_fk,
	    a.adq_pk 
	FROM
	    adquisicion a
	ORDER BY adq_pre_fk, adq_pk
  ) as A on adq.adq_pk = A.adq_pk
set adq.orden = A.num;