import { ref, computed, reactive } from 'vue';

export function useCarComparison(cars:any) {
    const transposedData = computed(() => {
        const headers = Object.keys(cars[0]);
        const data = cars.map((car:any) => {
            const row = {};
            headers.forEach((header:any) => {
                row[header] = car[header];
            });
            return row;
        });
        const transposed = headers.map(header => {
            return {
                label: header,
                values: data.map((row:any) => row[header])
            };
        });
        return [{ label: '参数', values: cars.map((car: any) => car.name) }, ...transposed];
    });

    return {
        transposedData
    };
}