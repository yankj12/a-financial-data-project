package com.yan.finance.fund.ods.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yan.finance.fund.ods.schema.FincOdsInvIndexDataHis;

@Mapper
public interface FincOdsInvIndexDataHisMapper {

	void insertFincInvIndexDataHis(FincOdsInvIndexDataHis fincInvIndexDataHis);
	
	void insertBatchFincInvIndexDataHis(List<FincOdsInvIndexDataHis> fincInvIndexDataHisList);
	
	FincOdsInvIndexDataHis findFincInvIndexDataHisByPK(FincOdsInvIndexDataHis fincInvIndexDataHis);
	
	List<FincOdsInvIndexDataHis> findFincInvIndexDataHisByCurrIdAndDateRange(Map<String, Object> condition);
	
	Long countFincInvIndexDataHisByCurrIdAndDateRange(Map<String, Object> condition);
	
	void deleteFincInvIndexDataHisByCurrIdAndDateRange(Map<String, Object> condition);
	
	void deleteBatchFincInvIndexDataHisByPK(List<FincOdsInvIndexDataHis> fincInvIndexDataHisList);
	
	void deleteFincInvIndexDataHisByPK(FincOdsInvIndexDataHis fincInvIndexDataHis);
	
}
