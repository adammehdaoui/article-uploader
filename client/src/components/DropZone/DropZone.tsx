import { Button } from "@/components/ui/button";
import { CardFooter } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { useForm } from "react-hook-form";
import "./DropZone.css";

import { useMutation, useQueryClient } from "@tanstack/react-query";
import axios from "axios";
import type { FormContent } from "../../validators/FormContent";

export default function DropZone({ id }: { id: string }) {
  const { register, handleSubmit } = useForm<FormContent>();
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: async (form: FormContent) => {
      const file = form.file[0];

      console.log(file);

      const formData = new FormData();
      formData.append("file", file);

      return axios
        .post(`http://localhost:8080/api/v1/${id}/image/upload`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((error) => {
          console.error("Erreur lors de la requête Axios :", error);
        });
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["articles"] });
    },
  });

  const onSubmit = (form: FormContent) => {
    mutation.mutate(form);
  };

  return (
    <CardFooter>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Input type="file" {...register("file")} />
        <Button type="submit" className="registerButton">
          Enregistrer
        </Button>
      </form>
    </CardFooter>
  );
}
