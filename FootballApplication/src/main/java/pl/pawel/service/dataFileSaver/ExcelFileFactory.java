package pl.pawel.service.dataFileSaver;

public class ExcelFileFactory implements IFileSaverFactory{

	@Override
	public IFileSaver createFile() {
		return new ExcelFileSaver();
	}

}
