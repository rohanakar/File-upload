package com.curl.hackathon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSetService {

	@Autowired
	private DataSetRepository dataSetRepository;
	
	public Object viewFileNames() {
		return dataSetRepository.findAll().stream().map(s->s.getFilename());
	}

	public String viewData(String filename) {
		List<DataSetModel> dataSetModel = dataSetRepository.findFile(filename);
		if(!isValid(dataSetModel)) {
			return "No such Filename found";
		}
		List<Data> dataSet= dataSetModel.get(0).getData();
		StringBuilder response = new StringBuilder();
		response.append(dataSetModel.get(0).getHeaders().stream()
                .collect(Collectors.joining("|")));
		for(Data data:dataSet) {
			response.append(data.getContent()+"\n");
		}
		return response.toString();
	}

	private boolean isValid(List<DataSetModel> dataSet) {
		return dataSet.size()==0? false: true;
	}

	public String upload(String filename, String file) {
		String dataSet[]=file.split("\\n");
		if(dataSet.length>20000)
			return "File has more than 20000 entries";
		try {
			DataSetModel dataSetModel = new DataSetModel();
			dataSetModel.setFilename(filename);
			dataSetModel.setHeaders( Arrays.asList(dataSet[0].split("\\|")));
			List<Data> dataSetList = new ArrayList<Data>();
			for(int i=1;i<dataSet.length;i++) {
				Data temp = new Data();
				temp.lineNum = i;
				temp.content=dataSet[i];
				dataSetList.add(temp);
			}
			dataSetModel.setData(dataSetList);
			dataSetRepository.save(dataSetModel);
		}
		catch(Exception e) {
			return "File format invlaid" + e;
		}
		return "DataSet added";
	}
}
