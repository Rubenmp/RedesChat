all: chat

chat: ./*.tex
	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chat.tex
	# bibtex memoria
	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chat.tex
	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chat.tex

	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chatBeamer.tex
	# bibtex memoria
	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chatBeamer.tex
	pdflatex -shell-escape -interaction=nonstopmode -file-line-error ./chatBeamer.tex
	make clean

clean:
	echo "Limpieza"
	find . -regex ".*~" -exec rm -r {} \;

	rm -f ./*.aux
	rm -f ./*.log
	rm -f ./*.nav
	rm -f ./*.out
	rm -f ./*.vrb
	rm -f ./*.snm
	rm -f ./*.toc
	rm -f ./*.gz
	rm -f ./*.blg
	rm -f ./*.bbl

mrproper: clean
	rm -f ./*.pdf
